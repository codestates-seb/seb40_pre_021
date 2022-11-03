import { useEffect } from 'react';
import { useState } from 'react';
import styled from 'styled-components';
import Answers from '../components/Mypage/Activity/Answers/Answers';
import Questions from '../components/Mypage/Activity/Questions/Questions';
import Reputation from '../components/Mypage/Activity/Reputation/Reputation';
import Sidebar from '../components/Mypage/Activity/Sidebar';
import Summary from '../components/Mypage/Activity/Summary';
import Tags from '../components/Mypage/Activity/Tags/Tags';
import useDynamicTitle from '../hooks/useDynamicTitle';
import useMypageData from '../hooks/useMypageData';

const MypageActivityPage = () => {
	const [tabs, setTabs] = useState(data);
	const [curTab, setCurTab] = useState(tabs[0]);

	useDynamicTitle('User', true);

	const handleTabChange = (id) => {
		let newTabs = tabs.map((tab) =>
			tab.id === id ? { ...tab, clicked: true } : { ...tab, clicked: false },
		);
		setTabs(newTabs);
	};

	const changeCurrentTab = ({ id, name }) => {
		let newCurTab = tabs.filter((tab) => tab.id === id || tab.name === name);
		setCurTab(...newCurTab);
	};

	const goToTop = () => {
		window.scrollTo({ top: 0 });
	};

	useEffect(() => {
		goToTop();
		handleTabChange(curTab.id);
	}, [curTab]);

	const TabContent = ({ curTab }) => {
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
							limit={5}
							handleTabChange={changeCurrentTab}
						/>
						<Questions
							question={question}
							limit={5}
							handleTabChange={changeCurrentTab}
						/>
						<Tags
							tag={tag}
							limit={5}
							flex={true}
							handleTabChange={changeCurrentTab}
						/>
						<Reputation flex={true} />
					</GridBox>
				);
			case 'Answers':
				return <Answers answer={answer} />;
			case 'Questions':
				return <Questions question={question} />;
			case 'Tags':
				return <Tags tag={tag} setTag={setTag} />;
			case 'Reputation':
				return <Reputation />;
		}
	};

	return (
		<Container>
			<Sidebar
				tabs={tabs}
				onChange={handleTabChange}
				onFilter={changeCurrentTab}
				onScrollTop={goToTop}
			/>
			<TabContent curTab={curTab.name} />
		</Container>
	);
};

export default MypageActivityPage;

const Container = styled.div`
	display: flex;
	margin: 8px 0;
	flex-wrap: nowrap;
	width: 100%;
`;

const GridBox = styled.div`
	display: grid;
	grid-template-columns: repeat(2, minmax(0, 1fr));
	gap: 24px;
	margin: 8px 0 8px 8px;
`;

let data = [
	{
		id: 0,
		name: 'Summary',
		clicked: true,
	},
	{
		id: 1,
		name: 'Answers',
		clicked: false,
	},
	{
		id: 2,
		name: 'Questions',
		clicked: false,
	},
	{
		id: 3,
		name: 'Tags',
		clicked: false,
	},
	{
		id: 4,
		name: 'Reputation',
		clicked: false,
	},
];
