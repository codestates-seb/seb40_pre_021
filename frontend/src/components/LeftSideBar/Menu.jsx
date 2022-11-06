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
	color: #666666;
	font-size: 13px;
	user-select: none;
	padding-left: ${(props) => props.padding || '0.3rem'};
	padding-top: 0.1rem;
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
					className={
						location.pathname.split('/')[1] === path.split('/')[1] && 'active'
					}>
					{name === 'Questions' && <RiEarthFill size="19px" />}
					&nbsp;
					{name}
				</Content>
			</Link>
		</MenuStyle>
	);
};

export default Menu;
