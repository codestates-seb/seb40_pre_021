import { useState } from 'react';
import styled from 'styled-components';

let data = [
	{
		id: 0,
		name: 'Profile',
		clicked: false,
	},
	{
		id: 1,
		name: 'Activity',
		clicked: true,
	},
	{
		id: 2,
		name: 'Saves',
		clicked: false,
	},
	{
		id: 3,
		name: 'Settings',
		clicked: false,
	},
];

const Navigation = () => {
	const [tabs, setTabs] = useState(data);

	const handleTabChange = (id) => {
		let newTabs = tabs.map((tab) =>
			tab.id === id ? { ...tab, clicked: true } : { ...tab, clicked: false },
		);
		setTabs(newTabs);
	};

	return (
		<>
			{tabs.map((tab) => {
				const { id, name, clicked } = tab;
				return (
					<Tab
						key={id}
						clicked={clicked}
						onClick={() => {
							handleTabChange(id);
						}}>
						{name}
					</Tab>
				);
			})}
		</>
	);
};

export default Navigation;

const Tab = styled.a`
	display: flex;
	align-items: center;
	padding: 6px 12px;
	border: none;
	box-shadow: none;
	cursor: pointer;
	border-radius: 1000px;
	white-space: nowrap;
	margin: 2px;
	text-decoration: none;
	font-weight: 500;
	color: ${(props) => (props.clicked ? 'white' : '#525960')};
	background-color: ${(props) => (props.clicked ? '#F48225' : 'white')};
	:hover {
		background-color: ${(props) => (props.clicked ? '#DA680C' : '#e4e6e8')};
	}
`;
