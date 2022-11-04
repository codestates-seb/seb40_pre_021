import { useState } from 'react';
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
} from '../../api/QuestionApi';

const Controller = ({
	kind,
	votecount,
	likeYn,
	unlikeYn,
	bookmark,
	choosed,
	QcreatorNickname,
	loginNickname,
}) => {
	const [vote, setVote] = useState(votecount); // 서버에서 가져온 votecount
	const [upVoted, setUpVoted] = useState(likeYn); // 서버에서 가져온 상태
	const [downVoted, setDownVoted] = useState(unlikeYn); // 서버에서 가져온 상태

	const [bookmarked, setBookmarked] = useState(bookmark);

	const [chosen, setChosen] = useState(choosed);

	const handleUpVote = () => {
		const upVote = { upVote: true, downVote: false };
		const cancelUpVote = { upVote: false, downVote: false };
		if (!upVoted) {
			setUpVoted(true);
			setVote(votecount + 1);
			kind === 'answer'
				? upVoteForA(JSON.stringify(upVote))
				: upVoteForQ(JSON.stringify(upVote));
		} else {
			setUpVoted(false);
			setVote(votecount);
			kind === 'answer'
				? upVoteForA(JSON.stringify(cancelUpVote))
				: upVoteForQ(JSON.stringify(cancelUpVote)); //한 번 더 하면 upVote 상태가 취소됨
		}
	};

	const handleDownVote = () => {
		const downVote = { upVote: false, downVote: true };
		const cancelDownVote = { upVote: false, downVote: false };
		if (!downVoted) {
			setDownVoted(true);
			setVote(votecount - 1);
			kind === 'answer'
				? downVoteForA(JSON.stringify(downVote))
				: downVoteForQ(JSON.stringify(downVote));
		} else {
			setDownVoted(false);
			setVote(votecount);
			kind === 'answer'
				? downVoteForA(JSON.stringify(cancelDownVote))
				: downVoteForQ(JSON.stringify(cancelDownVote));
		}
	};

	const handleBookmark = () => {
		const bookmarkreg = { nickname: loginNickname, bookmarked: bookmarked };
		if (bookmarked) {
			setBookmarked(false);
			kind === 'answer'
				? bookmarkDelA(JSON.stringify(bookmarkreg))
				: bookmarkDelQ(JSON.stringify(bookmarkreg));
		} else {
			setBookmarked(true);
			kind === 'answer'
				? bookmarkA(JSON.stringify(bookmarkreg))
				: bookmarkQ(JSON.stringify(bookmarkreg));
		}
	};

	const handleChose = () => {
		if (chosen) setChosen(false);
		if (!chosen) setChosen(true);
		choose();
	}; // 질문 선택 기능

	return (
		<>
			<Container>
				<Up onClick={handleUpVote} upVoted={upVoted}>
					<svg width="36" height="36" viewBox="0 0 36 36">
						<path d="M2 25h32L18 9 2 25Z"></path>
					</svg>
				</Up>
				<Votes>{vote}</Votes>
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
				{kind === 'answer' && (
					<Choosed
						QcreatorNickname={QcreatorNickname}
						loginNickname={loginNickname}
						chosen={chosen}>
						<button onClick={handleChose}>
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
`;
const Choosed = styled.div`
	button {
		display: ${(props) =>
			props.QcreatorNickname === props.loginNickname ? 'block' : 'none'};
		cursor: pointer;
	}
	div {
		display: ${(props) =>
			props.QcreatorNickname === props.loginNickname ? 'none' : 'block'};
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
