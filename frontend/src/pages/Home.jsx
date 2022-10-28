import { useEffect, useState } from 'react';
import styled from 'styled-components';
import ListContainer from '../components/List/ListContainer';
import ListHeader from '../components/List/ListHeader';
import Pagination from '../components/List/Pagination';
import { getList } from '../api/ListApi';

const HomeStyle = styled.div`
	width: 100%;
	display: flex;
	flex-direction: column;
`;
const Home = () => {
	const [data, setData] = useState([]);
	const [questionCount, setQuestionCount] = useState([]);

	useEffect(() => {
		getList().then((res) => {
			setData(res.data);
			setQuestionCount(res.questionCount);
		});
	}, []);
	const tabList = ['Hot', 'Week', 'Month'];
	return (
		<HomeStyle>
			<ListHeader
				title={'Top Questions'}
				tabList={tabList}
				questionCount={questionCount}
			/>
			<ListContainer data={data} type={'Home'} />
		</HomeStyle>
	);
};

export default Home;
