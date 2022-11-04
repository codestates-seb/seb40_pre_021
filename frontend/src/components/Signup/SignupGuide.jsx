import styled from 'styled-components';
import { RiQuestionnaireFill } from 'react-icons/ri';
import { ImPriceTags } from 'react-icons/im';
import { TiArrowUnsorted } from 'react-icons/ti';
import { IoMdTrophy } from 'react-icons/io';
const SignupGuideStyle = styled.div`
	width: 33%;

	div {
		margin-bottom: 20px;
	}
`;
const Title = styled.div`
	font-size: 25px;
	font-weight: bold;
`;
const Guide = styled.div`
	font-size: 16px;
	font-weight: 600;
	display: flex;
	align-items: center;

	p {
		margin-left: 5px;
	}
`;
const Advertisement = styled.div`
	color: gray;
	font-size: 14px;
	font-weight: 600;

	a {
		text-decoration: none;
		color: hsl(206, 100%, 52%);
	}
`;

const SignupGuide = () => {
	return (
		<SignupGuideStyle>
			<Title>Join the Stack Overflow community</Title>
			<Guide>
				<RiQuestionnaireFill color="hsl(206,100%,52%)" size="30" />
				<p>Get unstuck — ask a question</p>
			</Guide>
			<Guide>
				<TiArrowUnsorted color="hsl(206,100%,52%)" size="30" />
				<p>Unlock new privileges like voting and commenting</p>
			</Guide>
			<Guide>
				<ImPriceTags color="hsl(206,100%,52%)" size="30" />
				<p>Save your favorite tags, filters, and jobs</p>
			</Guide>
			<Guide>
				<IoMdTrophy color="hsl(206,100%,52%)" size="30" />
				<p>Earn reputation and badges</p>
			</Guide>
			<Advertisement>
				<p>
					Collaborate and share knowledge with a private group for FREE.
					<a
						href="https://stackoverflow.co/teams/?utm_source=so-owned&utm_medium=product&utm_campaign=free-50&utm_content=public-sign-up"
						target={'_blank'}
						rel="noreferrer">
						Get Stack Overflow for Teams free for up to 50 users.
					</a>
				</p>
			</Advertisement>
		</SignupGuideStyle>
	);
};

export default SignupGuide;
