import styled from 'styled-components';
import defaultImage from '../../../assets/images/userDefaultImage.png';
import { GoCheck } from 'react-icons/go';

const AnswerList = ({ answer }) => {
	console.log(answer);
	const { answerUser, answerBody, answerCreatedAt, vote, choosed } = answer;
	let splitDate = answerCreatedAt.split(' ');
	let date = `${splitDate[1]} ${splitDate[2]}, ${splitDate[3]} at ${splitDate[4]}`;
	return (
		<AnswerListBox>
			<AnswerVoteAnswerBox>
				<VotesBox>
					<span>{vote}</span>
					<span>votes</span>
				</VotesBox>
				{choosed ? (
					<AnswerBox choosed={choosed}>
						<GoCheck style={{ color: 'white' }} />
						<span>Accepted</span>
					</AnswerBox>
				) : null}
				<AnswerContent choosed={choosed}>{answerBody}</AnswerContent>
			</AnswerVoteAnswerBox>
			<AnswerAndUserInfoBox>
				<a href="123">View answer</a>
				<UserInfoBox>
					<UserImage src={defaultImage} alt="user-image" />
					<a href="1">{answerUser}</a>
					<time>asked</time>
					<span>{date}</span>
				</UserInfoBox>
			</AnswerAndUserInfoBox>
		</AnswerListBox>
	);
};

export default AnswerList;

const AnswerListBox = styled.div`
	position: relative;
	margin: 16px 1rem 0 1rem;
	padding: 0.5rem 0 0.5rem 1rem;
	::before {
		content: '';
		display: block;
		position: absolute;
		top: 0;
		left: 0;
		right: 0;
		width: 4px;
		height: 100%;
		border-radius: 8px;
		background-color: #c8ccd0;
	}
`;

const AnswerVoteAnswerBox = styled.div`
	display: flex;
	flex-direction: row;
	align-items: center;
	flex-wrap: wrap;
	gap: 6px;
`;

const AnswerContent = styled.p`
	width: ${(props) => props.choosed && '100%'};
	color: #525960;
	display: -webkit-box;
	-webkit-line-clamp: 4;
	-webkit-box-orient: vertical;
	overflow: hidden;
	margin-bottom: 8px;
	font-size: 15px;
	font-weight: 500;
`;

const AnswerAndUserInfoBox = styled.div`
	margin-top: 8px;
	display: flex;
	align-items: center;
	justify-content: space-between;
	flex-wrap: wrap;
	column-gap: 6px;
	row-gap: 8px;
	a {
		text-decoration: none;
		cursor: pointer;
		color: #0074cc;
		font-size: 14px;
		font-weight: 500;
		:hover {
			color: #0a95ff;
		}
	}
`;

const UserImage = styled.img`
	border-radius: 3px;
	width: 16px;
	height: 16px;
`;

const VotesBox = styled.div`
	color: #0c0d0e;
	display: inline-flex;
	gap: 0.3rem;
	align-items: center;
	justify-content: center;
	white-space: nowrap;
	border: 1px solid transparent;

	span {
		font-weight: 600;
		font-size: 14px;
		&:first-child {
			font-weight: 700;
		}
	}
`;

const UserInfoBox = styled.div`
	display: flex;
	justify-content: flex-end;
	align-items: center;
	gap: 4px;
	flex-wrap: wrap;
	margin-left: auto;
	grid-template-columns: auto 1fr;
	line-height: 1;
	a {
		margin: 2px;
		color: #0074cc;
		text-decoration: none;
		cursor: pointer;
		font-size: 13px;
		font-weight: 600;
		:hover {
			color: #0a95ff;
		}
	}
	time {
		color: #6a737c;
		font-size: 13px;
		font-weight: 600;
	}
	span {
		color: #6a737c;
		font-size: 13px;
		font-weight: 600;
	}
`;

const AnswerBox = styled.div`
	color: ${(props) => (props.choosed ? '#2E6F44' : 'white')};
	background-color: ${(props) => (props.choosed ? '#2E6F44' : 'white')};
	border: 1px solid #2e6f44;
	border-radius: 3px;
	padding: 4px;
	font-size: 14px;
	display: inline-flex;
	gap: 0.3rem;
	align-items: center;
	justify-content: center;
	white-space: nowrap;

	span {
		color: ${(props) => (props.choosed ? 'white' : '#2E6F44')};
		font-weight: 600;
		&:first-child {
			font-weight: 700;
		}
	}
`;
