import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

let data = [
	{
		id: 0,
		name: 'Profile',
		clicked: false,
		path: 'activity',
	},
	{
		id: 1,
		name: 'Activity',
		clicked: true,
		path: 'activity',
	},
	{
		id: 2,
		name: 'Saves',
		clicked: false,
		path: 'saves',
	},
	{
		id: 3,
		name: 'Settings',
		clicked: false,
		path: 'activity',
	},
];

const Navigation = () => {
	const [tabs, setTabs] = useState(data);
	const navigate = useNavigate();

	const handleTabChange = (id, path) => {
		let newTabs = tabs.map((tab) =>
			tab.id === id ? { ...tab, clicked: true } : { ...tab, clicked: false },
		);
		setTabs(newTabs);
		navigate(path);
	};

	return (
		<>
			{tabs.map((tab) => {
				const { id, name, clicked, path } = tab;
				return (
					<Tab
						key={id}
						clicked={clicked}
						onClick={() => {
							handleTabChange(id, path);
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
	font-size: 14px;
	color: ${(props) => (props.clicked ? 'white' : '#525960')};
	background-color: ${(props) => (props.clicked ? '#F48225' : 'white')};
	:hover {
		background-color: ${(props) => (props.clicked ? '#DA680C' : '#e4e6e8')};
	}
`;
