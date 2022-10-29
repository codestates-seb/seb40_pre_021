import { useState } from 'react';
import styled from 'styled-components';
import Menu from './Menu';

const LeftMenuStyle = styled.div`
	min-width: 164px;
	border-right: 1px solid rgb(209, 211, 215);
	padding-top: 25px;

	.public {
		color: gray;
		font-size: 13px;
		user-select: none;
		margin: 5%;
	}
	.active {
		background-color: rgb(239, 240, 241);
		color: black;
		border-right: 3px solid rgb(223, 126, 44);
		font-weight: bold;
	}
`;

const LeftMenu = () => {
	const [active, setActive] = useState('Home');
	const menus = [
		{
			id: 0,
			name: 'Home',
			path: '/',
		},
		{
			id: 1,
			name: 'Questions',
			path: '/Questions',
		},
		{
			id: 2,
			name: 'Tags',
			path: '/',
		},
		{
			id: 3,
			name: 'Users',
			path: '/users/activity',
		},
	];

	const props = {
		active,
		onClickMenu: (name) => setActive(name),
	};

	return (
		<LeftMenuStyle>
			{menus.map((ele) => {
				props.name = ele.name;
				props.path = ele.path;
				if (ele.id > 1) {
					props.padding = '15%';
				}
				if (ele.name === 'Home') {
					return (
						<div key={ele.id}>
							<Menu {...props} />
							<div className="public">&nbsp;PUBLIC</div>
						</div>
					);
				}
				return <Menu key={ele.id} {...props} />;
			})}
		</LeftMenuStyle>
	);
};

export default LeftMenu;
