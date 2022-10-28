import styled from 'styled-components';
import List from './List';
const Container = styled.div`
	border-top: 1px solid rgb(209, 211, 215);
`;
const ListContainer = ({ data, type }) => {
	return (
		<Container>
			{data &&
				data.map((ele, idx) => {
					return <List key={ele.questionId} data={ele} type={type} />;
				})}
		</Container>
	);
};

export default ListContainer;
