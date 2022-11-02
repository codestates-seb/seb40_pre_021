import { useEffect, useState } from 'react';
import styled from 'styled-components';
import ListHeader from '../components/List/ListHeader';
import { getList } from '../api/ListApi';
import Pagination from '../components/List/Pagination';
import List from '../components/List/List';
import useDynamicTitle from '../hooks/useDynamicTitle';
import Button from '../components/common/Button';

const QuestionsStyle = styled.div`
	width: 100%;
	display: flex;
	flex-direction: column;
`;

const Questions = () => {
	const [data, setData] = useState();
	const [questionCount, setQuestionCount] = useState();
	const tabList = ['Newest', 'Unanswered'];

	const [pageSize, setPageSize] = useState('15');
	const [pages, setPages] = useState([]);
	const [now, setNow] = useState(1);

	useDynamicTitle('Questions - Stack Overflow');

	const buttonProps = {
		color: `hsl(210,8%,25%)`,
		background: `white`,
		borderColor: `rgb(209, 211, 215)`,
		shadowPersent: '0',
		height: '25px',
	};
	const createPageButton = (data, type) => {
		let className;
		let callback;
		if (type === 'page') {
			className = `${data === now && 'active'}`;
			callback = setNow;
		} else if (type === 'pageSize') {
			className = `${data === pageSize && 'active'}`;
			callback = setPageSize;
		}
		return (
			<Button
				key={data}
				text={data}
				className={className}
				{...buttonProps}
				callback={() => callback(data)}
			/>
		);
	};
	const getListData = (now) => {
		const data = { page: now.toString(), pageSize };
		getList(data).then((res) => {
			setData(res.data);
			setQuestionCount(res.questionCount);
		});
	};
	useEffect(() => {
		if (now !== 1) {
			getListData(now);
		}
	}, [now]);
	useEffect(() => {
		setNow(1);
		getListData(1);
	}, [pageSize]);

	const calTotalpage = () => {
		let totalPage = Math.ceil(questionCount / pageSize);
		setPages(
			Array(totalPage)
				.fill()
				.map((v, i) => i + 1),
		);
	};
	const listHeaderProps = {
		title: 'All Questions',
		tabList,
		filter: 'true',
		questionCount: questionCount,
	};

	const paginationProps = {
		now,
		pages,
		pageSize,
		createPageButton,
		calTotalpage,
	};

	return (
		<QuestionsStyle>
			<ListHeader {...listHeaderProps} />
			{data &&
				data.map((ele) => {
					return <List key={ele.questionId} data={ele} type={'Questions'} />;
				})}
			{questionCount && <Pagination {...paginationProps} />}
		</QuestionsStyle>
	);
};

export default Questions;
