import styled from 'styled-components';
import Title from './Title';
import aside1 from '../../../assets/images/Mypage/aside1.png';
import aside2 from '../../../assets/images/Mypage/aside2.png';
import aside3 from '../../../assets/images/Mypage/aside3.png';
import SummaryAside from './SummaryAside';

const Summary = () => {
	return (
		<Container>
			<Title title="Summary" />
			<div>
				<FlexBox>
					<SummaryAside
						img={aside1}
						size={50}
						title={'Reputation is how the community thanks you'}
						content={
							"When users upvote your helpful posts, you'll earn reputation and unlock new privileges."
						}
						footer={AsideContentFooter1()}
						basis={25}
					/>
					<SummaryAside
						img={aside2}
						size={50}
						title={'Earn badges for helpful actions'}
						content={
							'Badges are bits of digital flair that you get when you participate in especially helpful ways.'
						}
						footer={AsideContentFooter2()}
						basis={16.6}
					/>
					<SummaryAside
						img={aside3}
						size={50}
						title={'Measure your impact'}
						content={
							'Your posts and helpful actions here help hundreds or thousands of people searching for help.'
						}
						basis={8.3}
					/>
				</FlexBox>
			</div>
		</Container>
	);
};

export default Summary;

function AsideContentFooter1() {
	return (
		<AsideContentFooter>
			Learn more about reputation and privileges
		</AsideContentFooter>
	);
}

function AsideContentFooter2() {
	return (
		<AsideContentFooterButton>
			Take the Tour and earn your first badge
		</AsideContentFooterButton>
	);
}

const Container = styled.div`
	grid-column-end: 3;
	grid-column-start: 1;
`;

const FlexBox = styled.div`
	display: flex;
	@media screen and (max-width: 980px) {
		flex-direction: column;
	}
	aside {
		&:first-child {
			margin-left: 0;
		}
		&:last-child {
			margin-right: 0;
		}
	}
`;

const AsideContentFooter = styled.p`
	line-height: 20px;
	color: #232629;
	font-size: 13px;
	padding: 0 8px;
	margin-top: 12px;
	font-weight: 400;
`;

const AsideContentFooterButton = styled.button`
	color: white;
	background-color: #0a95ff;
	box-shadow: inset 0 1px 0 0 hsl(0deg 0% 100% / 40%);
	padding: 0.8rem;
	border: 1px solid transparent;
	border-radius: 3px;
	font-weight: 400;
	text-align: center;
	cursor: pointer;
	font-size: 13px;
	:hover {
		background-color: #0074cc;
	}
`;
