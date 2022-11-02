import { useState } from 'react';
import styled from 'styled-components';

let data = [
	{
		id: 0,
		name: 'All saves',
		clicked: true,
	},
	{
		id: 1,
		name: 'For later',
		clicked: false,
	},
];

const Sidebar = () => {
	const [tabs, setTabs] = useState(data);

	const handleTabChange = (id) => {
		let newTabs = tabs.map((tab) =>
			tab.id === id ? { ...tab, clicked: true } : { ...tab, clicked: false },
		);
		setTabs(newTabs);
	};
	return (
		<Container>
			<ListBox>
				<List>
					{tabs.map((item) => {
						const { id, name, clicked } = item;
						return (
							<ListText
								key={id}
								clicked={clicked}
								onClick={() => {
									handleTabChange(id);
								}}>
								{name}
							</ListText>
						);
					})}
				</List>
			</ListBox>
		</Container>
	);
};

export default Sidebar;

const Container = styled.div`
	width: 210px;
	margin-right: 32px;
	flex: none;
`;

const ListBox = styled.ul`
	display: flex;
	flex-direction: column;
	flex-wrap: wrap;
	list-style: none;
`;

const List = styled.li`
	list-style: none;
`;

const ListText = styled.span`
	white-space: normal;
	background-color: ${(props) => (props.clicked ? '#f48225' : 'white')};
	color: ${(props) => (props.clicked ? 'white' : '#525960')};
	display: flex;
	align-items: center;
	padding: 8px 12px;
	border: none;
	box-shadow: none;
	cursor: pointer;
	border-radius: 1000px;
	font-weight: 400;
	font-size: 13px;
	:hover {
		background-color: ${(props) => (props.clicked ? '#DA680C' : '#e4e6e8')};
	}
`;
