import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import Button from '../common/Button';
import { useDispatch, useSelector } from 'react-redux'; // 1, 2.
import {
	logoutSuccess,
	selectIsLogin,
	selectUserInfo,
} from '../../modules/userReducer';
import { Logout } from '../../api/LogoutApi';

const RightMenuStlye = styled.li`
	display: flex;
	justify-content: center;
	align-items: center;
`;
const NoneLogin = styled.div`
	button {
		margin-right: 5px;
	}
`;
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
				<>
					nickname :{userInfo.nickname}
					<Button text="Log out" callback={logoutRequestHandler} />
				</>
			) : (
				<NoneLogin>
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
				</NoneLogin>
			)}
		</RightMenuStlye>
	);
};

export default RightMenu;
