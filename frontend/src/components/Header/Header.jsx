import { Link } from 'react-router-dom';
import styled from 'styled-components';
import { logo } from '../../assets/images/logo';
import RightMenu from './RightMenu';
import Search from './Search';

const HeaderStyle = styled.header`
	height: 48px;
	border-top: 3px solid rgb(223, 126, 44);
	background-color: rgb(248, 248, 248);
	box-shadow: 0 0 3px 0 rgb(202, 197, 197);

	ul {
		display: flex;
		justify-content: center;
		height: 100%;
		width: 100%;
	}
`;

const LogoStyle = styled.li`
	width: 170px;
	display: flex;
	justify-content: center;
	align-items: center;

	&:hover {
		background-color: rgb(224, 226, 228);
	}
	img {
		width: 90%;
		height: 80%;
	}
`;

const Header = () => {
	return (
		<HeaderStyle>
			<ul>
				<LogoStyle>
					<Link to="/">
						<img src={logo} alt="logo" />
					</Link>
				</LogoStyle>
				<Search />
				<RightMenu />
			</ul>
		</HeaderStyle>
	);
};

export default Header;
