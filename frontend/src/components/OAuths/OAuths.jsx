import styled from 'styled-components';
import Button from '../common/Button';
import { AiFillFacebook } from 'react-icons/ai';
import { BsGithub } from 'react-icons/bs';
import { FcGoogle } from 'react-icons/fc';

const OAuthListStyle = styled.div`
	margin: 10px 0;
	display: flex;
	flex-direction: column;

	button {
		margin: 5px;
		padding: 3% 70px;
	}
`;
const OAuths = () => {
	const OAuthList = [
		{
			id: 0,
			text: 'Log in with Google',
			color: 'black',
			background: 'white',
			borderColor: 'rgb(216,217,220)',
			icon: <FcGoogle size="20" />,
		},

		{
			id: 1,
			text: 'Log in with GitHub',
			background: 'rgb(48,51,55)',
			icon: <BsGithub color="white" size="20" />,
		},
		{
			id: 2,
			text: 'Log in with Facebook',
			background: 'rgb(61,81,145)',
			icon: <AiFillFacebook color="white" size="20" />,
		},
	];
	return (
		<OAuthListStyle>
			{OAuthList.map((ele) => {
				return (
					<Button
						key={ele.id}
						text={ele.text}
						color={ele.color}
						borderColor={ele.borderColor}
						background={ele.background}
						icon={ele.icon}
						shadowPersent="0"
						radius="5px"
					/>
				);
			})}
		</OAuthListStyle>
	);
};

export default OAuths;
