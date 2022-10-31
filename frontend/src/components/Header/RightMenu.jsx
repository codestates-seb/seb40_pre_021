import { useNavigate, Link } from 'react-router-dom';
import styled from 'styled-components';
import Button from '../common/Button';
import { useDispatch, useSelector } from 'react-redux'; // 1, 2.
import {
	logoutSuccess,
	selectIsLogin,
	selectUserInfo,
} from '../../modules/userReducer';
import { Logout } from '../../api/LogoutApi';
import Avatar from '../Mypage/UserProfile/Avatar';

const RightMenuStlye = styled.li`
	display: flex;
	justify-content: center;
	align-items: center;
`;
const Box = styled.div`
	display: flex;
	justify-content: center;
	align-items: center;
	button {
		margin-left: 5px;
	}

	a {
		text-decoration: none;
	}
`;
const AavatarBox = styled.div``;
const RightMenu = () => {
	//임시 state 로그인 했는지 확인
	const isLogin = useSelector(selectIsLogin);
	const userInfo = useSelector(selectUserInfo);

	const navigate = useNavigate();
	const dispatch = useDispatch();

	const logoutRequestHandler = () => {
		//api
		Logout(userInfo).then((res) => {
			//store의 데이터 지워버림
			dispatch(logoutSuccess());
			//로그인 화면으로 이동
			return navigate('/login');
		});
	};
	return (
		<RightMenuStlye>
			{isLogin ? (
				<Box>
					<Link to="/users/activity">
						<Avatar
							nickname={userInfo.nickname}
							padding="10px"
							width="auto"
							heigth="33px"
							fontSize="1.3rem"
						/>
					</Link>

					<Button
						text="Log out"
						color={`hsl(205,47%,42%)`}
						background={`hsl(205,46%,92%)`}
						borderColor={`hsl(205,41%,63%)`}
						shadowPersent={'70%'}
						height={'33px'}
						callback={logoutRequestHandler}
					/>
				</Box>
			) : (
				<Box>
					<Button
						text="Log in"
						color={`hsl(205,47%,42%)`}
						background={`hsl(205,46%,92%)`}
						borderColor={`hsl(205,41%,63%)`}
						shadowPersent={'70%'}
						height={'33px'}
						callback={() => navigate('/login')}
					/>
					<Button
						text="Sign up"
						height={'33px'}
						callback={() => navigate('/signup')}
					/>
				</Box>
			)}
		</RightMenuStlye>
	);
};

export default RightMenu;
