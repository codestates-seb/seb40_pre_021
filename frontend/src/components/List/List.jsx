import { useState } from 'react';
import styled from 'styled-components';
import { Link, useNavigate } from 'react-router-dom';
import Button from '../common/Button';
import timeForToday from '../../utils/timeForToday';
import { GoCheck } from 'react-icons/go';
import Avatar from '../Mypage/UserProfile/Avatar';

const ListStyle = styled.div`
	border-top: 1px solid rgb(209, 211, 215);
	padding: 15px 15px 30px 15px;
	display: flex;
`;
const LeftSection = styled.div`
	padding-right: 15px;
	flex: 2;
	display: flex;
	flex-direction: column;
	align-items: flex-end;
	font-size: 0.85rem;

	div {
		margin: 3px;
		font-weight: 400;
	}

	.vote {
	}

	.answer {
		color: ${(props) =>
			props.choosed ? 'white' : props.answerCount ? '#2E6F44' : '#6A737C'};
		background-color: ${(props) => (props.choosed ? '#2E6F44' : 'white')};
		border: ${(props) => (props.answerCount ? '1px solid #2e6f44' : 'none')};
		border-radius: 3px;
		padding: 4px;
		display: inline-flex;
		gap: 0.2rem;
		align-items: center;
		justify-content: center;
		white-space: nowrap;
		font-weight: 400;
	}
	.views {
		color: hsl(210, 8%, 45%);
	}
`;
const RightSection = styled.div`
	padding-right: 10%;
	flex: 8;
	display: flex;
	flex-direction: column;
	align-items: flex-start;

	div {
		margin: 3px;
	}
	a {
		text-decoration: none;
		color: hsl(209, 100%, 37.5%);
		:hover {
			color: #0087fe;
		}
	}
	.title {
		color: hsl(209, 100%, 37.5%);
		font-size: 1.1rem;
		line-height: 1.3;
		font-weight: 400;
		cursor: pointer;
	}
	.body {
		color: hsl(210, 8%, 25%);
		overflow: hidden;
		text-overflow: ellipsis;
		display: -webkit-box;
		-webkit-line-clamp: 2;
		-webkit-box-orient: vertical;
		word-wrap: break-word;
		line-height: 1.3;
		height: 2.4em;
		word-break: break-all;
	}
	.bottomBox {
		width: 100%;
		display: flex;
		justify-content: space-between;
	}
	.tags {
		display: flex;

		button {
			display: flex;
			align-items: center;
			margin-right: 5px;
			margin-bottom: 15px;
		}
	}
	.create {
		display: flex;
		align-items: center;
		color: hsl(210, 8%, 45%);
		margin-top: 10px;

		a {
			margin-right: 10px;
			margin-left: 5px;
		}
	}
`;
const List = ({ data, type }) => {
	// const [questionId, setQuestionId] = useState(data.questionId);
	const [createId, setCreateId] = useState(data.nickname);
	const [choosed, setChoosed] = useState(data.chooseYn);
	const navigate = useNavigate();

	const buttonProps = {
		color: `hsl(205,47%,42%)`,
		background: `hsl(205,46%,92%)`,
		shadowPersent: '0',
		height: '12px',
	};
	return (
		<ListStyle>
			<LeftSection choosed={choosed} answerCount={data.answerCount}>
				<div className="vote">{data.vote} votes</div>
				<div className="answer">
					{choosed && <GoCheck />}
					{data.answerCount} {data.answerCount === 1 ? 'answer' : 'answers'}
				</div>
				<div className="views">{data.views} views</div>
			</LeftSection>
			<RightSection>
				<div
					className="title"
					role="presentation"
					onClick={() => navigate(`/questions/question/${data.id}`)}>
					{data.title}
				</div>
				{type === 'Questions' && (
					<div className="body">{data.contents.replace(/<[^>]*>?/g, '')}</div>
				)}
				<div className="bottomBox">
					<div className="tags">
						{Array.isArray(data.questionsTags) &&
							data.questionsTags.map((ele) => {
								return (
									<Button
										key={ele.tagId}
										text={ele.title}
										{...buttonProps}
										callback={() => navigate(`/search/[${ele.title}]`)}
									/>
								);
							})}
					</div>
					<div className="create">
						<Avatar
							nickname={createId}
							width="27px"
							heigth="27px"
							fontSize={`${20 - 2 * createId?.length}px`}
						/>
						<Link to="">{createId}</Link>
						asked {timeForToday(data.createdAt, 'ago')}
					</div>
				</div>
			</RightSection>
		</ListStyle>
	);
};

export default List;
