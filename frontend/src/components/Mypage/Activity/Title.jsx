import styled from 'styled-components';

const Title = ({ title, number = '', flex, handleTabChange }) => {
	const lowerTitle = title?.toLowerCase();
	const localeNumber = number?.toLocaleString('ko-KR');
	return (
		<Container flex={flex}>
			{handleTabChange ? (
				<>
					<StyledTitle>{title}</StyledTitle>
					<Text
						onClick={() => {
							handleTabChange({ name: title });
						}}>
						View all {localeNumber} {lowerTitle}
					</Text>
				</>
			) : (
				<StyledTitle>{localeNumber + ' ' + title}</StyledTitle>
			)}
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
	font-size: 21px;
	font-weight: 400;
`;

const Text = styled.span`
	color: #6f757d;
	font-size: 13px;
	font-weight: 400;
	:hover {
		color: #525960;
		cursor: pointer;
	}
`;
