import { useEffect, useState } from 'react';
import styled from 'styled-components';
import ListHeader from '../components/List/ListHeader';
import { getList } from '../api/ListApi';
import List from '../components/List/List';
import { Link } from 'react-router-dom';

const HomeStyle = styled.div`
	width: 100%;
	display: flex;
	flex-direction: column;
`;
const More = styled.div`
	margin-top: 36px;
	padding: 36px;
	font-size: 1.1em;
	font-weight: 600;

	a {
		text-decoration: none;
		color: hsl(209, 100%, 37.5%);
	}
`;
const Home = () => {
	const [data, setData] = useState([]);

	useEffect(() => {
		getList().then((res) => {
			setData(res.data);
		});
	}, []);
	const tabList = ['Hot', 'Week', 'Month'];
	return (
		<HomeStyle>
			<ListHeader title={'Top Questions'} tabList={tabList} />
			{data &&
				data.map((ele) => {
					return <List key={ele.questionId} data={ele} type={'Home'} />;
				})}
			<More>
				Looking for more? Browse the{' '}
				<Link to="/questions">complete list of questions</Link>
			</More>
		</HomeStyle>
	);
};

export default Home;
