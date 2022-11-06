import styled, { css } from 'styled-components';

const ListBox = ({ lists, text, component }) => {
	return (
		<Container lists={lists}>
			<Box lists={lists}>
				{lists?.length ? component : <EmptyText>{text}</EmptyText>}
			</Box>
		</Container>
	);
};

export default ListBox;

const Container = styled.div`
	/* height: 100%; */
	border: 1px solid #d7d9dc;
	border-radius: 5px;
	overflow: hidden;
	${(props) =>
		(props.lists?.length < 1 || !props.lists) &&
		css`
			display: flex;
			justify-content: center;
			align-items: center;
		`}
`;

const Box = styled.div`
	display: flex;
	padding: ${(props) => (props.lists?.length > 0 ? '0' : '48px')};
	justify-content: ${(props) =>
		props.lists?.length > 0 ? 'space-between' : 'center'};
	align-items: ${(props) =>
		props.lists?.length > 0 ? 'flex-start' : 'center'};
	text-align: left;
`;

const EmptyText = styled.p`
	color: #6a737c;
	text-align: center;
	margin: 0 auto;
	font-size: 13px;
	font-weight: 400;
`;
