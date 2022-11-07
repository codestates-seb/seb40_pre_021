import styled from 'styled-components';
import AnswerOrQuestionList from '../AnswerOrQuestionList';
import ListBox from '../ListBox';
import SortButtonGroup from '../../common/SortButtonGroup';
import Title from '../Title';

let sortData = [
	{
		id: 0,
		name: 'Newest',
		clicked: true,
	},
	{
		id: 1,
		name: 'Score',
		clicked: false,
	},
];

const Answers = ({ answer, setAnswer, limit, handleTabChange }) => {
	return (
		<Container>
			<TitleBox>
				<Title
					title="Answers"
					number={answer?.length}
					handleTabChange={handleTabChange}
				/>
				<SortButtonGroup menus={sortData} data={answer} callback={setAnswer} />
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
		width: 100%;
		display: flex;
		flex-direction: column;
	}
`;

const TitleBox = styled.div`
	display: flex;
	margin-bottom: 8px;
	align-items: flex-end;
	flex-wrap: wrap;
	justify-content: space-between;
`;
