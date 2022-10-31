import { useState } from 'react';
import styled from 'styled-components';

const SortButtonGroup = ({ data }) => {
	const [menu, setMenu] = useState(data);

	const handleMenuChange = (id) => {
		let newMenu = menu.map((item) =>
			item.id === id ? { ...item, clicked: true } : { ...item, clicked: false },
		);
		setMenu(newMenu);
	};

	return (
		<GroupContainer>
			{menu.map((item) => {
				const { id, name, clicked } = item;
				return (
					<Menu
						key={id}
						clicked={clicked}
						onClick={() => {
							handleMenuChange(id);
						}}>
						{name}
					</Menu>
				);
			})}
		</GroupContainer>
	);
};

export default SortButtonGroup;

const GroupContainer = styled.div`
	display: flex;
	flex-wrap: wrap;
	margin-bottom: 1px;
	margin-right: 1px;
	border-radius: 3px;
`;

const Menu = styled.a`
	padding: 0.6em;
	font-size: 12px;
	font-weight: 600;
	box-shadow: none;
	border: 1px solid #838c95;
	margin-right: -1px;
	border-radius: 0;
	background-color: ${(props) => (props.clicked ? '#e3e6e8' : 'white')};
	color: ${(props) => (props.clicked ? '#3b4045' : '#6a737c')};
	:hover {
		background-color: ${(props) => (props.clicked ? '#e3e6e8' : '#f8f9f9')};
	}
	&:first-child {
		border-top-left-radius: 3px;
		border-bottom-left-radius: 3px;
	}
	&:last-child {
		border-top-right-radius: 3px;
		border-bottom-right-radius: 3px;
	}
`;
