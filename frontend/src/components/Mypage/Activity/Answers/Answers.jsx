import styled from 'styled-components';
import AnswerOrQuestionList from '../AnswerOrQuestionList';
import ListBox from '../ListBox';
import SortButtonGroup from '../SortButtonGroup';
import Title from '../Title';

let sortData = [
	{
		id: 0,
		name: 'Score',
		clicked: true,
	},
	{
		id: 1,
		name: 'Activity',
		clicked: false,
	},
	{
		id: 2,
		name: 'Newest',
		clicked: false,
	},
];

const Answers = ({ answer, limit }) => {
	return (
		<Container>
			<TitleBox>
				<Title title="Answers" number={answer?.length} />
				<SortButtonGroup data={sortData} />
			</TitleBox>
			<ListBox
				text="You have not answered any questions"
				lists={answer}
				component={<AnswerOrQuestionList lists={answer} limit={limit} />}
			/>
		</Container>
	);
};

export default Answers;

const Container = styled.div`
	&&& {
		height: 100%;
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
