import styled from 'styled-components';
import { RiEarthFill } from 'react-icons/ri';
import { Link, useLocation } from 'react-router-dom';
const MenuStyle = styled.div`
	a {
		text-decoration: none;
		color: inherit;
	}
`;
const Content = styled.div`
	height: 34px;
	color: gray;
	font-size: 15px;
	user-select: none;
	padding-left: ${(props) => props.padding || '5%'};
	padding-top: 1%;
	padding-bottom: 1%;
	display: flex;
	align-items: center;
	cursor: pointer;

	&:hover {
		color: black;
	}
`;
const Menu = ({ name, padding, path }) => {
	const location = useLocation();
	return (
		<MenuStyle>
			<Link to={path}>
				<Content
					padding={padding}
					className={location.pathname === path && 'active'}>
					{name === 'Questions' && <RiEarthFill size="17px" maring="5px" />}
					&nbsp;
					{name}
				</Content>
			</Link>
		</MenuStyle>
	);
};

export default Menu;
