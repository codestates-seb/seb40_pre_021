import { useState, useEffect } from 'react';
import styled from 'styled-components';
import SavesLayout from '../components/Mypage/Saves/SavesLayout';
import Sidebar from '../components/Mypage/Saves/Sidebar';

const MypageSavesPage = () => {
	return (
		<Container>
			<Sidebar />
			<SavesLayout />
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
