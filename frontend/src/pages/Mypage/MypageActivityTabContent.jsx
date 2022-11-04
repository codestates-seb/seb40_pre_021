import styled from 'styled-components';
import Answers from '../../components/Mypage/Activity/Answers/Answers';
import Questions from '../../components/Mypage/Activity/Questions/Questions';
import Reputation from '../../components/Mypage/Activity/Reputation/Reputation';
import Summary from '../../components/Mypage/Activity/Summary';
import Tags from '../../components/Mypage/Activity/Tags/Tags';
import TotalList from '../../components/Mypage/Activity/TotalList';
import useMypageData from '../../hooks/useMypageData';

const MypageTabContent = ({ curTab, changeCurrentTab }) => {
	const [answer, setAnswer] = useMypageData('answer');
	const [question, setQuestion] = useMypageData('question');
	const [tag, setTag] = useMypageData('tag');

	const handleSortLists = (name, data, callback, tag = false) => {
		let newData = [];
		switch (name) {
			case 'Score':
				if (tag) {
					newData = [...data].sort((a, b) => b.tagCount - a.tagCount);
				} else {
					newData = [...data].sort((a, b) => b.vote - a.vote);
				}
				break;
			case 'Newest':
				newData = [...data].sort(function compare(a, b) {
					const dateA = new Date(a.createdAt).getTime();
					const dateB = new Date(b.createdAt).getTime();
					if (dateA > dateB) return -1;
					if (dateA < dateB) return 1;
					return 0;
				});
				break;
			case 'Name':
				newData = [...data].sort(function compare(a, b) {
					if (a.title < b.title) return -1;
					if (a.title > b.title) return 1;
					return 0;
				});
				break;
			case 'Views':
				newData = [...data].sort(function compare(a, b) {
					if (a.views > b.views) return -1;
					if (a.views < b.views) return 1;
					return 0;
				});
				break;
		}
		callback(newData);
	};

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
						handleSortLists={handleSortLists}
					/>
					<Questions
						question={question}
						setQuestion={setQuestion}
						limit={5}
						handleTabChange={changeCurrentTab}
						handleSortLists={handleSortLists}
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
					handleSortLists={handleSortLists}
					type="answer"
				/>
			);
		case 'Questions':
			return (
				<TotalList
					lists={question}
					title="Questions"
					callback={setQuestion}
					handleSortLists={handleSortLists}
				/>
			);
		case 'Tags':
			return (
				<Tags tag={tag} setTag={setTag} handleSortLists={handleSortLists} />
			);
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
`;
