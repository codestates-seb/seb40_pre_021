import styled from 'styled-components';
import SavesLayout from '../../components/Mypage/Saves/SavesLayout';
import Sidebar from '../../components/Mypage/Saves/Sidebar';
import useDynamicTitle from '../../hooks/useDynamicTitle';
import useMypageData from '../../hooks/useMypageData';

const MypageSavesPage = () => {
	const [bookmark, setBookmark] = useMypageData('bookmark');

	useDynamicTitle('Saves for', true);

	const handleSortLists = (name, data, callback) => {
		let newData = [];
		switch (name) {
			case 'Newest':
				newData = [...data].sort(function compare(a, b) {
					const dateA = new Date(a.createdAt).getTime();
					const dateB = new Date(b.createdAt).getTime();
					if (dateA > dateB) return -1;
					if (dateA < dateB) return 1;
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
			// case 'Activity':
			// 	newData = [...data].sort(function compare(a, b) {
			// 		if (a.choosed < b.choosed) return -1;
			// 		if (a.choosed > b.choosed) return 1;
			// 		return 0;
			// 	});
			// 	break;
		}
		callback(newData);
	};

	return (
		<Container>
			<Sidebar />
			<SavesLayout
				bookmark={bookmark}
				setBookmark={setBookmark}
				handleSortLists={handleSortLists}
			/>
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
