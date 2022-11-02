import { useEffect, useState } from 'react';
import styled from 'styled-components';
import ListHeader from '../components/List/ListHeader';
import { getList } from '../api/ListApi';
import List from '../components/List/List';
import { Link } from 'react-router-dom';
import useDynamicTitle from '../hooks/useDynamicTitle';

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
	const tabList = ['Hot', 'Week', 'Month'];
	const [tab, setTab] = useState('Hot');
	useDynamicTitle(
		'Stack Overflow - Where Developers Learn, Share, & Build Careers',
	);
	useEffect(() => {
		const data = { tab: tab.toLowerCase() };
		getList(data).then((res) => {
			setData(res.data);
		});
	}, [tab]);

	const listHeaderProps = {
		title: 'Top Questions',
		tabList,
		tab,
		setTab,
	};
	return (
		<HomeStyle>
			<ListHeader {...listHeaderProps} />
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
