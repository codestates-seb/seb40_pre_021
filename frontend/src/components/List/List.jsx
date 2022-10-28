import { useState } from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import Button from '../common/Button';

const ListStyle = styled.div`
	padding: 15px 15px 30px 15px;
	border-bottom: 1px solid rgb(209, 211, 215);
	display: flex;
`;
const LeftSection = styled.div`
	padding-right: 15px;
	flex: 2;
	display: flex;
	flex-direction: column;
	align-items: flex-end;

	div {
		margin: 3px;
		font-weight: 600;
	}

	.vote {
	}

	.answer {
		color: hsl(210, 8%, 45%);
	}
	.views {
		color: hsl(210, 8%, 45%);
	}
`;
const RightSection = styled.div`
	flex: 8;
	display: flex;
	flex-direction: column;
	align-items: flex-start;

	div {
		margin: 3px;
	}
	.title {
		a {
			font-size: 1.2em;
			font-weight: 600;
			text-decoration: none;
			color: hsl(209, 100%, 37.5%);
		}
	}
	.body {
		height: 34px;
		overflow: hidden;
		color: hsl(210, 8%, 25%);
		text-overflow: ellipsis;
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
		padding-right: 10px;
		display: flex;
		align-items: flex-end;
		color: hsl(210, 8%, 45%);
	}
`;
const List = ({ data, type }) => {
	// const [questionId, setQuestionId] = useState(data.questionId);
	const [createId, setCreateId] = useState(data.createId);
	const [choosed, setChoosed] = useState(data.choosed);

	const buttonProps = {
		color: `hsl(205,47%,42%)`,
		background: `hsl(205,46%,92%)`,
		shadowPersent: '0',
		height: '12px',
	};
	return (
		<ListStyle>
			<LeftSection>
				<div className="vote">{data.votes} votes</div>
				<div className="answer">{data.answerCount} answer</div>
				<div className="views">{data.views} views</div>
			</LeftSection>
			<RightSection>
				<div className="title">
					<Link to="/">{data.title}</Link>
				</div>
				{type === 'Questions' && <div className="body">{data.body}</div>}
				<div className="bottomBox">
					<div className="tags">
						{Array.isArray(data.tags) ? (
							data.tags.map((ele, idx) => {
								return <Button key={idx} text={ele} {...buttonProps} />;
							})
						) : (
							<Button text={data.tags} {...buttonProps} />
						)}
					</div>
					<div className="create">
						{createId}
						{data.createdAt}
					</div>
				</div>
			</RightSection>
		</ListStyle>
	);
};

export default List;
