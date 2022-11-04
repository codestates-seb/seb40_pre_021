import { useEffect } from 'react';
import styled from 'styled-components';
import Sidebar from '../../components/Mypage/Activity/Sidebar';
import useDynamicTitle from '../../hooks/useDynamicTitle';
import MypageTabContent from './MypageActivityTabContent';
import useCurrentTab from './hooks/useCurrentTab';
import useTabs from './hooks/useTabs';

const MypageActivityPage = () => {
	const [tabs, onChangeTab] = useTabs(data);
	const [curTab, onChangeCurTab] = useCurrentTab(data);
	useDynamicTitle('User', true);

	const goToTop = () => {
		window.scrollTo({ top: 0 });
	};

	useEffect(() => {
		goToTop();
		onChangeTab(curTab.id);
	}, [curTab]);

	return (
		<Container>
			<Sidebar
				tabs={tabs}
				onChange={onChangeTab}
				onFilter={onChangeCurTab}
				onScrollTop={goToTop}
			/>
			<MypageTabContent
				curTab={curTab.name}
				changeCurrentTab={onChangeCurTab}
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
