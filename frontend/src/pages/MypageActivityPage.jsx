import { useEffect, useState } from 'react';
import styled from 'styled-components';
import { getMypageInfo } from '../api/mypageApi';
import Answers from '../components/Mypage/Activity/Answers/Answers';
import Questions from '../components/Mypage/Activity/Questions/Questions';
import Reputation from '../components/Mypage/Activity/Reputation/Reputation';
import Sidebar from '../components/Mypage/Activity/Sidebar';
import Summary from '../components/Mypage/Activity/Summary';
import Tags from '../components/Mypage/Activity/Tags/Tags';

const MypageActivityPage = () => {
	const [info, setInfo] = useState({ nickname: '', createdAt: '' });
	const { answers, tags, questions } = info;

	useEffect(() => {
		getMypageInfo(1).then((res) => {
			setInfo(res);
		});
	}, []);
	return (
		<SideBarBox>
			<Sidebar />
			<GridBox>
				<Summary />
				<Answers lists={answers} />
				<Questions lists={questions} />
				<Tags lists={tags} />
				<Reputation />
			</GridBox>
		</SideBarBox>
	);
};

export default MypageActivityPage;

const SideBarBox = styled.div`
	display: flex;
	margin: 8px 0;
	flex-wrap: nowrap;
`;

const GridBox = styled.div`
	display: grid;
	grid-template-columns: repeat(2, minmax(0, 1fr));
	gap: 24px;
	margin: 8px 0 8px 8px;
`;
