import { useEffect, useState } from 'react';
import styled from 'styled-components';
import ListHeader from '../components/List/ListHeader';
import { getList } from '../api/ListApi';
import Pagination from '../components/List/Pagination';
import List from '../components/List/List';

const QuestionsStyle = styled.div`
	width: 100%;
	display: flex;
	flex-direction: column;
`;

const Questions = () => {
	const [data, setData] = useState();
	const [questionCount, setQuestionCount] = useState();

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
			{data &&
				data.map((ele) => {
					return <List key={ele.questionId} data={ele} type={'Questions'} />;
				})}
			{questionCount && <Pagination questionCount={questionCount} />}
		</QuestionsStyle>
	);
};

export default Questions;
