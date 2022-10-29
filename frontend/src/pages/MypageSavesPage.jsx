import { useState } from 'react';
import { useEffect } from 'react';
import styled from 'styled-components';
import { getMypageInfo } from '../api/mypageApi';
import SavesLayout from '../components/Mypage/Saves/SavesLayout';
import Sidebar from '../components/Mypage/Saves/Sidebar';

const MypageSavesPage = () => {
	const [info, setInfo] = useState({ nickname: '', createdAt: '' });
	const { bookmarks } = info;

	useEffect(() => {
		getMypageInfo(1).then((res) => {
			setInfo(res);
		});
	}, []);
	return (
		<Container>
			<Sidebar />
			<SavesLayout bookmarks={bookmarks} />
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
