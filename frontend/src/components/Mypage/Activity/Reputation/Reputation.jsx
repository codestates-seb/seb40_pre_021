import styled from 'styled-components';
import ListBox from '../ListBox';
import Title from '../Title';

const Reputation = () => {
	return (
		<Container>
			<TitleBox>
				<Title title="Reputation" />
			</TitleBox>
			<ListBox text="You have no recent reputation changes." />
		</Container>
	);
};

export default Reputation;

const Container = styled.div`
	&&& {
		height: 100%;
		display: flex;
		flex-direction: column;
		width: 100%;
	}
`;

const TitleBox = styled.div`
	display: flex;
	margin-bottom: 8px;
	align-items: flex-end;
	justify-content: space-between;
`;
