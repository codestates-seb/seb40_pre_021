import styled from 'styled-components';

const ButtonStyle = styled.button`
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
	padding: 0.8em;
	border: 1px solid ${(props) => props.borderColor || 'transparent'};
	border-radius: ${(props) => props.radius || '3px'};
	font-family: inherit;
	font-size: 13px;
	font-weight: bold;
	line-height: calc(15 / 13);
	text-align: center;
	text-decoration: none;
	cursor: pointer;
	user-select: none;
	box-shadow: inset 0 1px 0 0
		hsl(0deg 0% 100% / ${(props) => props.shadowPersent || '40%'});
	background-color: ${(props) => props.background || 'hsl(206,100%,52%)'};
	color: ${(props) => props.color || 'hsl(0,0%,100%);'};
	height: ${(props) => props.height || ''};

	&:hover {
		filter: brightness(90%);
		/* color: white; */
	}

	.icon {
		margin-right: 3px;
	}
`;
//기본은 Ask Question (푸른색)색깔
//버튼 색상 변경 필요 시 props 로 borderColor,background,color,shadowPersent 받아서 필요한것만 바꾸면됨
const Button = ({ text, callback, icon, className, ...props }) => {
	return (
		<ButtonStyle {...props} onClick={callback} className={className}>
			{icon && <div className="icon">{icon}</div>}
			{text}
		</ButtonStyle>
	);
};

export default Button;
