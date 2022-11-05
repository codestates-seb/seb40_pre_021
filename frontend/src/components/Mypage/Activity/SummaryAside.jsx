import styled from 'styled-components';

const SummaryAside = ({ img, size, title, content, footer, basis }) => {
	return (
		<Aside basis={basis}>
			<ContentBox>
				<img src={img} alt="" width={size} height={size} />
				<AsideTitle>{title}</AsideTitle>
				<AsideContent>{content}</AsideContent>
				{footer}
			</ContentBox>
		</Aside>
	);
};

export default SummaryAside;

const Aside = styled.aside`
	flex-basis: ${(props) => props.basis && `calc(${props.basis}% - 16px)`};
	flex-grow: 1;
	margin: 8px;
	padding: 12px;
	border-radius: 5px;
	border: 1px solid #d7d9dc;

	@media screen and (max-width: 980px) {
		margin: 8px 0;
	}
`;

const ContentBox = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	text-align: center;
	padding: 24px 0;
	gap: 8px;
`;

const AsideTitle = styled.h2`
	font-size: 15px;
	margin-bottom: 2px;
	flex: 0 auto;
	font-weight: 400;
	line-height: 16px;
`;

const AsideContent = styled.p`
	line-height: 20px;
	color: #6a737c;
	font-size: 12px;
	padding: 0 8px;
	margin-bottom: 12px;
	font-weight: 400;
	line-height: 16px;
`;
