import styled, { css } from 'styled-components';

const ListBox = ({ lists, text, component }) => {
	return (
		<Container lists={lists}>
			<Box lists={lists}>
				{lists ? component : <EmptyText>{text}</EmptyText>}
			</Box>
		</Container>
	);
};

export default ListBox;

const Container = styled.div`
	flex-grow: 1;
	border: 1px solid #d7d9dc;
	border-radius: 5px;
	overflow: hidden;
	${(props) =>
		!props.lists &&
		css`
			display: flex;
			justify-content: center;
			align-items: center;
		`}
`;

const Box = styled.div`
	display: flex;
	padding: ${(props) => (props.lists ? '0' : '24px')};
	justify-content: ${(props) => (props.lists ? 'space-between' : 'center')};
	align-items: ${(props) => (props.lists ? 'flex-start' : 'center')};
	text-align: left;
`;

const EmptyText = styled.p`
	height: 100%;
	color: #6a737c;
	text-align: center;
	margin: 0 auto;
	font-size: 13px;
`;
