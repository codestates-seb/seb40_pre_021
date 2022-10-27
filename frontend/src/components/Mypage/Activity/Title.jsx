import styled from 'styled-components';

const Title = ({ text }) => {
	return (
		<Container>
			<StyledTitle>{text}</StyledTitle>
		</Container>
	);
};

export default Title;

const Container = styled.div`
	display: flex;
	margin-bottom: 8px;
`;

const StyledTitle = styled.h2`
	font-size: 23px;
	font-weight: 500;
`;
