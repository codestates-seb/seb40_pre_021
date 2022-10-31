import { useState } from 'react';
import styled from 'styled-components';

const Sidebar = () => {
	const [tabs, setTabs] = useState(data);

	const handleTabChange = (id) => {
		let newTabs = tabs.map((tab) =>
			tab.id === id ? { ...tab, clicked: true } : { ...tab, clicked: false },
		);
		setTabs(newTabs);
	};

	return (
		<Box>
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
		</Box>
	);
};

export default Sidebar;

const Box = styled.div`
	display: flex;
	flex-direction: column;
	margin: 8px 0;
`;

const Tab = styled.a`
	display: flex;
	align-items: center;
	padding: 8px 48px 8px 12px;
	border: none;
	box-shadow: none;
	cursor: pointer;
	border-radius: 1000px;
	white-space: nowrap;
	margin: 0 32px 0 0;
	text-decoration: none;
	font-weight: 500;
	font-size: 0.9rem;
	color: ${(props) => (props.clicked ? '#242629' : '#525960')};
	background-color: ${(props) => (props.clicked ? '#F1F2F3' : 'white')};
	:hover {
		background-color: ${(props) => (props.clicked ? '#E4E6E8' : '#e4e6e8')};
	}
`;

let data = [
	{
		id: 0,
		name: 'Summary',
		clicked: true,
	},
	{
		id: 1,
		name: 'Answers',
		clicked: false,
	},
	{
		id: 2,
		name: 'Questions',
		clicked: false,
	},
	{
		id: 3,
		name: 'Tags',
		clicked: false,
	},
	{
		id: 4,
		name: 'Articles',
		clicked: false,
	},
	{
		id: 5,
		name: 'Badges',
		clicked: false,
	},
	{
		id: 6,
		name: 'Following',
		clicked: false,
	},
	{
		id: 7,
		name: 'Bounties',
		clicked: false,
	},
	{
		id: 8,
		name: 'Reputation',
		clicked: false,
	},
	{
		id: 9,
		name: 'All actions',
		clicked: false,
	},
	{
		id: 10,
		name: 'Responses',
		clicked: false,
	},
	{
		id: 11,
		name: 'Votes',
		clicked: false,
	},
];
