import styled from 'styled-components';
import List from '../components/List/List';
import ListHeader from '../components/List/ListHeader';

const HomeStyle = styled.div`
	width: 100%;
	display: flex;
	flex-direction: column;
`;
const Home = () => {
	const tabList = ['Hot', 'Week', 'Month'];
	return (
		<HomeStyle>
			<ListHeader title={'Top Questions'} tabList={tabList} />
			<List />
		</HomeStyle>
	);
};

export default Home;
