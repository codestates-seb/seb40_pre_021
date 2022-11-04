import { useEffect, useState } from 'react';
import styled from 'styled-components';
import Sidebar from '../../components/Mypage/Activity/Sidebar';
import useDynamicTitle from '../../hooks/useDynamicTitle';
import MypageTabContent from './MypageActivityTabContent';

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

	return (
		<Container>
			<Sidebar
				tabs={tabs}
				onChange={handleTabChange}
				onFilter={changeCurrentTab}
				onScrollTop={goToTop}
			/>
			<MypageTabContent
				curTab={curTab.name}
				changeCurrentTab={changeCurrentTab}
			/>
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
