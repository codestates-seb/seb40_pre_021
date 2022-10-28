import { useState, useEffect } from 'react';
import styled from 'styled-components';
import { getMypageInfo } from '../api/mypageApi';
import Avatar from '../components/Mypage//UserProfile/Avatar';
import ProfileButton from '../components/Mypage/ProfileButton';
import UserNickname from '../components/Mypage/UserProfile/UserNickname';
import UserInfo from '../components/Mypage/UserProfile/UserInfo';
import useDate from '../hooks/useDate';
import { RiPencilFill } from 'react-icons/ri';
import { BiMessageDetail } from 'react-icons/bi';
import Navigation from '../components/Mypage/Navigation';
import Summary from '../components/Mypage/Activity/Summary';
import Sidebar from '../components/Mypage/Activity/Sidebar';
import Answers from '../components/Mypage/Activity/Answers/Answers';
import Questions from '../components/Mypage/Activity/Questions/Questions';
import Tags from '../components/Mypage/Activity/Tags/Tags';
import Reputation from '../components/Mypage/Activity/Reputation/Reputation';
import { Outlet } from 'react-router-dom';

const Mypage = () => {
	const [info, setInfo] = useState({ nickname: '', createdAt: '' });
	const { nickname, createdAt, answers, tags, questions } = info;
	const [date] = useDate(createdAt);

	useEffect(() => {
		getMypageInfo(1).then((res) => {
			setInfo(res);
		});
	}, []);

	return (
		<Container>
			<Box>
				<Avatar nickname={nickname} />
				<UserInfoBox>
					<UserNickname nickname={nickname} />
					<UserInfo date={date} />
				</UserInfoBox>
				<ProfileBtnArea>
					<ProfileButton
						text="Edit profile"
						icon={<RiPencilFill size={18} />}
					/>
					<ProfileButton
						text="Newwork profile"
						icon={<BiMessageDetail size={18} />}
					/>
				</ProfileBtnArea>
			</Box>
			<Box>
				<Navigation />
			</Box>
			<Outlet />
		</Container>
	);
};

export default Mypage;

const Container = styled.div`
	display: flex;
	flex-wrap: wrap;
	padding: 24px;
	max-width: 1100px;
	width: calc(100% - 164px);
`;

const Box = styled.div`
	width: 100%;
	display: flex;
	flex-wrap: wrap;
	margin: 8px 0;
`;

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

const UserInfoBox = styled.div`
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: flex-start;
	flex-wrap: wrap;
	margin: 8px 12px;
`;

const ProfileBtnArea = styled.div`
	position: absolute;
	right: 24px;
	top: 65px;
	display: flex;
`;
