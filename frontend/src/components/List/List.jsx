import { useState } from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

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
			text-decoration: none;
			color: hsl(209, 100%, 37.5%);
		}
	}
	.body {
		color: hsl(210, 8%, 25%);
	}
	.tags {
	}
	.createdAt {
		color: hsl(210, 8%, 45%);
	}
`;
const List = ({ data, type }) => {
	const [questionId, setQuestionId] = useState(data.questionId);
	const [createId, setCreateId] = useState(data.createId);
	const [choosed, setChoosed] = useState(data.choosed);
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
				<div className="tags">{data.tags}</div>
				<div className="createdAt">
					{data.createId}
					{data.createdAt}
				</div>
			</RightSection>
		</ListStyle>
	);
};

export default List;
