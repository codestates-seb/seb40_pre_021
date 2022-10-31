import styled from 'styled-components';

const Title = ({ title, number, flex }) => {
	const lowerTitle = title?.toLowerCase();
	const localeNumber = number?.toLocaleString('ko-KR');
	return (
		<Container flex={flex}>
			<StyledTitle>{title}</StyledTitle>
			{number ? (
				<Text>
					View all {localeNumber} {lowerTitle}
				</Text>
			) : null}
		</Container>
	);
};

export default Title;

const Container = styled.div`
	display: ${(props) => props.flex && 'flex'};
	justify-content: ${(props) => props.flex && 'space-between'};
	align-items: ${(props) => props.flex && 'flex-end'};
	width: ${(props) => props.flex && '100%'};
`;

const StyledTitle = styled.h2`
	font-size: 23px;
	font-weight: 600;
	font-size: 22px;
`;

const Text = styled.span`
	color: #6f757d;
	font-size: 14px;
	font-weight: 600;
`;
