import { useEffect, useState } from 'react';
import styled from 'styled-components';
import ListHeader from '../components/List/ListHeader';
import { getList } from '../api/ListApi';
import ListContainer from '../components/List/ListContainer';
import Pagination from '../components/List/Pagination';

const QuestionsStyle = styled.div`
	width: 100%;
	display: flex;
	flex-direction: column;
`;

const Questions = () => {
	const [data, setData] = useState([]);
	const [questionCount, setQuestionCount] = useState([]);

	useEffect(() => {
		getList().then((res) => {
			setData(res.data);
			setQuestionCount(res.questionCount);
		});
	}, []);
	const tabList = ['Newest', 'Unanswered'];
	return (
		<QuestionsStyle>
			<ListHeader
				title={'All Questions'}
				tabList={tabList}
				filter={'true'}
				questionCount={questionCount}
			/>
			<ListContainer data={data} type={'Questions'} />
			<Pagination />
		</QuestionsStyle>
	);
};

export default Questions;
