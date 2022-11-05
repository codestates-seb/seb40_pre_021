import styled from 'styled-components';
import Answers from '../../components/Mypage/Activity/Answers/Answers';
import Questions from '../../components/Mypage/Activity/Questions/Questions';
import Reputation from '../../components/Mypage/Activity/Reputation/Reputation';
import Summary from '../../components/Mypage/Activity/Summary';
import Tags from '../../components/Mypage/Activity/Tags/Tags';
import TotalList from '../../components/Mypage/Activity/TotalList';
import useMypageData from './hooks/useMypageData';

const MypageTabContent = ({ curTab, changeCurrentTab }) => {
	const [answer, setAnswer] = useMypageData('answer');
	const [question, setQuestion] = useMypageData('question');
	const [tag, setTag] = useMypageData('tag');

	switch (curTab) {
		case 'Summary':
			return (
				<GridBox>
					<Summary />
					<Answers
						answer={answer}
						setAnswer={setAnswer}
						limit={5}
						handleTabChange={changeCurrentTab}
					/>
					<Questions
						question={question}
						setQuestion={setQuestion}
						limit={5}
						handleTabChange={changeCurrentTab}
					/>
					<Tags
						tag={tag}
						setTag={setTag}
						limit={5}
						flex={true}
						handleTabChange={changeCurrentTab}
					/>
					<Reputation flex={true} />
				</GridBox>
			);
		case 'Answers':
			return (
				<TotalList
					lists={answer}
					title="Answers"
					callback={setAnswer}
					type="answered"
				/>
			);
		case 'Questions':
			return (
				<TotalList lists={question} title="Questions" callback={setQuestion} />
			);
		case 'Tags':
			return <Tags tag={tag} setTag={setTag} />;
		case 'Reputation':
			return <Reputation />;
	}
};

export default MypageTabContent;

const GridBox = styled.div`
	display: grid;
	grid-template-columns: repeat(2, minmax(0, 1fr));
	gap: 24px;
	margin: 8px 0 8px 8px;
	@media screen and (max-width: 1264px) {
		display: flex;
		flex-direction: column;
	}
`;
