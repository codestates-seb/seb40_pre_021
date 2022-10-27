import styled from 'styled-components';
import Title from './Title';
import aside1 from '../../../assets/images/Mypage/aside1.png';

const Summary = () => {
	return (
		<div>
			<Title text="Summary" />
			<Container>
				<FlexBox>
					<SummaryAside>
						<ContentBox>
							<img src={aside1} alt="" width={48} height={48} />
							<AsideTitle>
								Reputation is how the community thanks you
							</AsideTitle>
							<AsideContent>
								When users upvote your helpful posts, you`ll earn reputation and
								unlock new privileges.
							</AsideContent>
							<AsideContentFooter>
								Learn more about reputation and privileges
							</AsideContentFooter>
						</ContentBox>
					</SummaryAside>
				</FlexBox>
			</Container>
		</div>
	);
};

export default Summary;

const Container = styled.div`
	grid-column-end: 3;
	grid-column-start: 1;
`;

const FlexBox = styled.div`
	display: flex;
	margin: 16px;
`;

const SummaryAside = styled.aside`
	flex-basis: 25% - 16px;
	flex-grow: 1;
	margin: 16px;
	padding: 12px;
	border-radius: 5px;
	border: 1px solid #d7d9dc;
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
	font-size: 1.1rem;
	margin-bottom: 2px;
	flex: 0 auto;
	font-weight: 500;
`;

const AsideContent = styled.p`
	line-height: 20px;
	color: #6a737c;
	font-size: 0.9rem;
	padding: 0 8px;
	margin-bottom: 12px;
	font-weight: 500;
`;

const AsideContentFooter = styled.p`
	line-height: 20px;
	color: #232629;
	font-size: 0.9rem;
	padding: 0 8px;
	margin-top: 12px;
	font-weight: 500;
`;
