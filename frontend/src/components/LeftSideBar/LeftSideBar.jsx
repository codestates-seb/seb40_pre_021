import { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import styled from 'styled-components';
import { selectIsLogin } from '../../modules/userReducer';
import Menu from './Menu';

const Container = styled.div`
	border-right: 1px solid rgb(209, 211, 215);
`;

const LeftMenuStyle = styled.div`
	min-width: 164px;
	padding-top: 25px;
	position: sticky;
	top: 52px;
	max-height: 100vh;
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
	const isLogin = useSelector(selectIsLogin);
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
			name: 'Users',
			path: '/users/activity',
		},
	];
	const props = {};

	return (
		<Container>
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
					if (ele.name === 'Users') {
						return isLogin && <Menu key={ele.id} {...props} />;
					}
					return <Menu key={ele.id} {...props} />;
				})}
			</LeftMenuStyle>
		</Container>
	);
};

export default LeftMenu;
