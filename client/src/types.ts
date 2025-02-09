export interface Campaign {
  id?: number;
  name: string;
  keywords: string[];
  bidAmount: number;
  campaignFund: number;
  status: boolean;
  town: string;
  radius: number;
}
