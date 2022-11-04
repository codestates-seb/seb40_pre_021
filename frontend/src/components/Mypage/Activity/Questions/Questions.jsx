import styled from 'styled-components';
import AnswerOrQuestionList from '../AnswerOrQuestionList';
import ListBox from '../ListBox';
import SortButtonGroup from '../../common/SortButtonGroup';
import Title from '../Title';

let sortData = [
	{
		id: 0,
		name: 'Score',
		clicked: true,
	},
	{
		id: 1,
		name: 'Newest',
		clicked: false,
	},
	{
		id: 2,
		name: 'Views',
		clicked: false,
	},
];

const Questions = ({ question, setQuestion, limit, handleTabChange }) => {
	return (
		<Container>
			<TitleBox>
				<Title
					title="Questions"
					number={question?.length}
					handleTabChange={handleTabChange}
				/>
				<SortButtonGroup
					menus={sortData}
					data={question}
					callback={setQuestion}
				/>
			</TitleBox>
			<ListBox
				text="You have not asked any questions"
				lists={question}
				component={<AnswerOrQuestionList lists={question} limit={limit} />}
			/>
		</Container>
	);
};

export default Questions;

const Container = styled.div`
	&&& {
		height: 100%;
		width: 100%;
		display: flex;
		flex-direction: column;
	}
`;

const TitleBox = styled.div`
	display: flex;
	margin-bottom: 8px;
	align-items: flex-end;
	justify-content: space-between;
`;
