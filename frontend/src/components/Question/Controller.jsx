import { useEffect, useState } from 'react';
import styled from 'styled-components';
import {
	choose,
	downVoteForA,
	downVoteForQ,
	upVoteForA,
	upVoteForQ,
	bookmarkA,
	bookmarkQ,
	bookmarkDelA,
	bookmarkDelQ,
	getQuestion,
} from '../../api/QuestionApi';
import useBookmark from '../../hooks/useBookmark';
import useVoteStatus from '../../hooks/useVote';

const Controller = ({
	kind,
	votecount,
	votedata,
	bookmarkdata,
	chose,
	QcreatorNickname,
	loginNickname,
	questionId,
	answerId,
	setThread,
	questionChose,
}) => {
	// 아래 2개의 커스텀 훅이 백엔드와 통신하여 가져온 정보는
	// 그 아래 3개의 상태와 useEffect를 통해 로컬 정보로 전환됩니다.
	const [upVoteStatus, downVoteStatus] = useVoteStatus(votedata, loginNickname);
	const [bookmarkStatus] = useBookmark(bookmarkdata, loginNickname);

	const [upVoted, setUpVoted] = useState(false);
	const [downVoted, setDownVoted] = useState(false);
	const [bookmarked, setBookmarked] = useState(false);

	useEffect(() => {
		if (upVoteStatus || downVoteStatus) {
			setUpVoted(upVoteStatus);
			setDownVoted(downVoteStatus);
		}
	}, [upVoteStatus, downVoteStatus]);

	useEffect(() => {
		if (bookmarkStatus) {
			setBookmarked(bookmarkStatus);
		}
	}, [bookmarkStatus]);

	const getThread = (res, reload) => {
		if (!res.code) {
			getQuestion(questionId).then((res) => {
				setThread(res);
				if (reload) {
					window.location.reload();
				}
			});
		}
	};
	const handleUpVote = () => {
		const upVote = { likeYn: true, unlikeYn: false, questionId, answerId };
		const cancelUpVote = {
			likeYn: false,
			unlikeYn: false,
			questionId,
			answerId,
		}; // 정보를 하드코딩된 상태로 고정하여 보냅니다.
		if (!upVoted) {
			setUpVoted(true);
			setDownVoted(false);
			kind === 'answer'
				? upVoteForA(upVote).then((res) => getThread(res, false))
				: upVoteForQ(upVote).then((res) => getThread(res, false));
		} else {
			setUpVoted(false);
			kind === 'answer'
				? upVoteForA(cancelUpVote).then((res) => getThread(res, false))
				: upVoteForQ(cancelUpVote).then((res) => getThread(res, false)); //한 번 더 누르면 upVote 상태가 취소됨
		}
	};

	const handleDownVote = () => {
		const downVote = { likeYn: false, unlikeYn: true, questionId, answerId };
		const cancelDownVote = {
			likeYn: false,
			unlikeYn: false,
			questionId,
			answerId,
		};
		if (!downVoted) {
			setDownVoted(true);
			setUpVoted(false);
			kind === 'answer'
				? downVoteForA(downVote).then((res) => getThread(res, false))
				: downVoteForQ(downVote).then((res) => getThread(res, false));
		} else {
			setDownVoted(false);
			kind === 'answer'
				? downVoteForA(cancelDownVote).then((res) => getThread(res, false))
				: downVoteForQ(cancelDownVote).then((res) => getThread(res, false));
		}
	};

	const handleBookmark = () => {
		const bookmarkreg = {
			nickname: loginNickname,
			bookmarked: bookmarked,
			questionId,
			answerId,
		}; // 유저 닉네임과 북마크 상태를 저장하여 보냅니다.
		if (bookmarked) {
			setBookmarked(false);
			kind === 'answer' ? bookmarkDelA(bookmarkreg) : bookmarkDelQ(bookmarkreg);
		} else {
			setBookmarked(true);
			kind === 'answer';
			kind === 'answer' ? bookmarkA(bookmarkreg) : bookmarkQ(bookmarkreg);
		}
	};

	const handleChoose = () => {
		if (!chose) {
			choose({ questionId, answerId }).then((res) => {
				getThread(res, false);
			}); // 채택 여부를 저장하여 보냅니다.
		} else alert('답변 채택은 하나만 가능하다.');
	};

	return (
		<>
			<Container>
				<Up onClick={handleUpVote} upVoted={upVoted}>
					<svg width="36" height="36" viewBox="0 0 36 36">
						<path d="M2 25h32L18 9 2 25Z"></path>
					</svg>
				</Up>
				<Votes>{votecount}</Votes>
				<Down onClick={handleDownVote} downVoted={downVoted}>
					<svg width="36" height="36" viewBox="0 0 36 36">
						<path d="M2 11h32L18 27 2 11Z"></path>
					</svg>
				</Down>
				<Bookmark onClick={handleBookmark} bookmarked={bookmarked}>
					<svg width="18" height="18" viewBox="0 0 18 18">
						<path d="m9 10.6 4 2.66V3H5v10.26l4-2.66ZM3 17V3c0-1.1.9-2 2-2h8a2 2 0 0 1 2 2v14l-6-4-6 4Z"></path>
					</svg>
				</Bookmark>
				{kind ===
					'answer' /* 컨트롤러가 질문 컨트롤러인지, 답변 컨트롤러인지를 확인하여 답변 컨트롤러일 때에만 채택 부분을 활성화합니다.*/ && (
					<Choosed
						QcreatorNickname={QcreatorNickname}
						loginNickname={loginNickname}
						questionChose={questionChose}
						chosen={chose}>
						<button onClick={handleChoose}>
							<svg width="36" height="36" viewBox="0 0 36 36">
								<path d="m6 14 8 8L30 6v8L14 30l-8-8v-8Z"></path>
							</svg>
						</button>
						<div>
							<svg width="36" height="36" viewBox="0 0 36 36">
								<path d="m6 14 8 8L30 6v8L14 30l-8-8v-8Z"></path>
							</svg>
						</div>
					</Choosed>
				)}
				<History>
					<svg width="19" height="18" viewBox="0 0 19 18">
						<path d="M3 9a8 8 0 1 1 3.73 6.77L8.2 14.3A6 6 0 1 0 5 9l3.01-.01-4 4-4-4h3L3 9Zm7-4h1.01L11 9.36l3.22 2.1-.6.93L10 10V5Z"></path>
					</svg>
				</History>
			</Container>
		</>
	);
};

const Container = styled.div`
	width: 36px;
	display: flex;
	flex-direction: column;
	justify-content: flex-start;
	align-items: center;
`;
const Up = styled.button`
	cursor: pointer;
	svg {
		display: block;
		background-color: white;
		fill: ${(props) =>
			props.upVoted === true ? 'rgb(229, 136, 62);' : 'rgb(187, 191, 195)'};
	}
	:active {
		svg {
			fill: rgb(229, 136, 62);
		}
	}
`;
const Votes = styled.div`
	margin: 0.5rem 0 0.5rem;
	font-size: 1.5rem;
	color: rgb(108, 115, 123);
`;
const Down = styled.button`
	cursor: pointer;
	svg {
		display: block;
		background-color: white;
		fill: ${(props) =>
			props.downVoted === true ? 'rgb(229, 136, 62);' : 'rgb(187, 191, 195)'};
	}

	:active {
		svg {
			fill: rgb(229, 136, 62);
		}
	}
`;
const Bookmark = styled.button`
	cursor: pointer;
	margin: 0.5rem 0 0.5rem 0;
	svg {
		display: block;
		background-color: white;
		fill: ${(props) =>
			props.bookmarked === true ? 'rgb(229, 136, 62);' : 'rgb(187, 191, 195)'};
	}
`;
// 아래의 채택 컴포넌트는 아이디와 질문 작성자를 비교하여 같을 때에는 버튼으로, 아닐 때에는 div로 렌더링됩니다.
const Choosed = styled.div`
	button {
		display: ${(props) =>
			props.QcreatorNickname === props.loginNickname &&
			props.questionChose === false
				? 'block'
				: 'none'};
		cursor: pointer;
	}
	div {
		display: ${(props) => (props.chosen === true ? 'block' : 'none')};
	}
	svg {
		display: block;
		background-color: white;
		fill: ${(props) =>
			props.chosen === true ? 'rgb(64, 110, 72)' : 'rgb(187, 191, 195)'};
	}
`;
const History = styled.button`
	cursor: pointer;
	margin: 0.5rem 0 0.5rem 0;
	svg {
		display: block;
		background-color: white;
		fill: rgb(187, 191, 195);
	}
`;
export default Controller;
