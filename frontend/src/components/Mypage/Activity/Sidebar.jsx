import styled from 'styled-components';

const Sidebar = ({ tabs, onChange, onFilter, onScrollTop }) => {
	return (
		<Box>
			{tabs.map((tab) => {
				const { id, name, clicked } = tab;
				return (
					<Tab
						key={id}
						clicked={clicked}
						onClick={() => {
							onChange(id);
							onFilter(id);
							onScrollTop();
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
	flex-wrap: wrap;
	margin: 8px 0;
	position: sticky;
	top: 64px;
	height: 150px;
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
	font-weight: 400;
	font-size: 13px;
	color: ${(props) => (props.clicked ? '#242629' : '#525960')};
	background-color: ${(props) => (props.clicked ? '#F1F2F3' : 'white')};
	:hover {
		background-color: ${(props) => (props.clicked ? '#E4E6E8' : '#e4e6e8')};
	}
`;
