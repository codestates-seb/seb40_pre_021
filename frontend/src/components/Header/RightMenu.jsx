import { useState } from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import Button from '../common/Button';

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
	const [loginState, setLoginState] = useState(false);
	return (
		<RightMenuStlye>
			{loginState ? (
				`프로필`
			) : (
				<NoneLogin>
					<Link to="/login">
						<Button
							text="Log in"
							color={`hsl(205,47%,42%)`}
							background={`hsl(205,46%,92%)`}
							borderColor={`hsl(205,41%,63%)`}
							shadowPersent={'70%'}
							height={'33px'}
						/>
					</Link>
					<Link to="/signup">
						<Button text="Sign up" height={'33px'} />
					</Link>
				</NoneLogin>
			)}
		</RightMenuStlye>
	);
};

export default RightMenu;
