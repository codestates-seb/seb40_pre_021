import styled from 'styled-components';
import Button from '../common/Button';
const ListTabStyle = styled.div`
	display: flex;
`;
const Tab = styled.div`
	border: 1px solid rgb(179, 183, 188);
	width: 99%;
	border-radius: 3px;
	background-color: white;
	display: flex;
	justify-content: center;
	align-items: center;

	button {
		padding: 10px;
		width: 100%;
		height: 100%;
		background-color: white;

		&:hover {
			filter: brightness(90%);
		}
	}
	.non-last {
		border-right: 1px solid rgb(179, 183, 188);
	}
`;

//filter : boolean
const ListTab = ({ tabList, filter }) => {
	return (
		<ListTabStyle filter={filter}>
			<Tab>
				{tabList.map((ele, idx) => {
					return (
						<button
							key={idx}
							className={tabList.length - 1 !== idx && 'non-last'}>
							{ele}
						</button>
					);
				})}
			</Tab>
			&nbsp;
			{filter && (
				<Button
					text="filter"
					color={`hsl(205,47%,42%)`}
					background={`hsl(205,46%,92%)`}
					borderColor={`hsl(205,41%,63%)`}
					shadowPersent={'70%'}
				/>
			)}
		</ListTabStyle>
	);
};

export default ListTab;
