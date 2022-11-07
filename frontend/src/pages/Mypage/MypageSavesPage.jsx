import styled from 'styled-components';
import SavesLayout from '../../components/Mypage/Saves/SavesLayout';
import Sidebar from '../../components/Mypage/Saves/Sidebar';
import useDynamicTitle from '../../hooks/useDynamicTitle';
import useTabs from './hooks/useTabs';

const MypageSavesPage = () => {
	const [tabs, onChange] = useTabs(data);
	useDynamicTitle('Saves for', true);

	return (
		<Container>
			<Sidebar tabs={tabs} onChange={onChange} />
			<SavesLayout />
		</Container>
	);
};

export default MypageSavesPage;

const Container = styled.div`
	width: 100%;
	display: flex;
	text-align: left;
	position: relative;
	flex: 1 0 auto;
	margin: 0 auto;
`;

let data = [
	{
		id: 0,
		name: 'All saves',
		clicked: true,
	},
	{
		id: 1,
		name: 'For later',
		clicked: false,
	},
];
