import styled from 'styled-components';
import SavesLayout from '../components/Mypage/Saves/SavesLayout';
import Sidebar from '../components/Mypage/Saves/Sidebar';
import useDynamicTitle from '../hooks/useDynamicTitle';
import useMypageData from '../hooks/useMypageData';

const MypageSavesPage = () => {
	const [bookmark, setBookmark] = useMypageData('bookmark');

	useDynamicTitle('Saves for', true);

	return (
		<Container>
			<Sidebar />
			<SavesLayout bookmark={bookmark} />
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
